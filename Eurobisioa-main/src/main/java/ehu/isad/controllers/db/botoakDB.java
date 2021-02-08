package ehu.isad.controllers.db;

import ehu.isad.model.Herrialdea;
import ehu.isad.model.Ordezkaritza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class botoakDB {
    private static botoakDB instantzia = new botoakDB();

    public static botoakDB getInstantzia(){
        return instantzia;
    }

    private botoakDB (){}

    //Important methods.
    public void puntuakGehitu(String herrialdea, Integer zenbat){
        String query = "UPDATE Ordezkaritza SET puntuak="+zenbat+" WHERE herrialdea='"+herrialdea+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public List<Ordezkaritza> erakutsiAukerak(Herrialdea herrialdea) throws SQLException {
        List<Ordezkaritza> lista = new ArrayList<>();
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs;
        rs = dbKudeatzaile.execSQL("select * from Ordezkaritza where Ordezkaritza.herrialdea != '"+herrialdea.getIzena()+"'");
        while(rs.next()){
            String abeslaria  = rs.getString("artista");
            String herr = rs.getString("herrialdea");
            int urtea = rs.getInt("urtea");
            String abestia = rs.getString("abestia");
            int puntuak = rs.getInt("puntuak");
            Ordezkaritza ordezkaritza = new Ordezkaritza(abeslaria,herr,urtea,abestia,puntuak);
            lista.add(ordezkaritza);
        }
        return lista;
    }
    public boolean bozkatuDezake(String herrialdea) throws SQLException {

        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs;
        rs = dbKudeatzaile.execSQL("select sum(puntuak) from Bozkaketa where bozkatuDu='"+herrialdea+"'" );

        if(rs.getInt("p")<5) return true;
        return false;

    }

}
