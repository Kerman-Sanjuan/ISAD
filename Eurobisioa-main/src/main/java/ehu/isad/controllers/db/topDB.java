package ehu.isad.controllers.db;

import ehu.isad.model.Ordezkaritza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class topDB {


    private static topDB instantzia = new topDB();

    public static topDB getInstantzia(){
        return instantzia;
    }

    private topDB(){}
    public List<Ordezkaritza>  top3() throws SQLException {
        int i = 0;
        List<Ordezkaritza> ordezkaritza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        String query = "select * from Ordezkaritza o  order by puntuak desc";
        //select * from Ordezkaritza o  where urtea == strftime('%Y','now') and etorrikoDa = 'Bai'.order by puntuak desc
        ResultSet rs = dbkud.execSQL(query);
        while (i<+4  &&  rs.next()){
            String artista = rs.getString("artista");
            String herrialdea = rs.getString("herrialdea");
            int urtea = rs.getInt("urtea");
            String abestia = rs.getString("abestia");
            int puntuak = rs.getInt("puntuak");
            Ordezkaritza ordezkaritzatu = new Ordezkaritza(artista,herrialdea,urtea,abestia,puntuak);
            ordezkaritza.add(ordezkaritzatu);
        }
        return  ordezkaritza;
    }
}
