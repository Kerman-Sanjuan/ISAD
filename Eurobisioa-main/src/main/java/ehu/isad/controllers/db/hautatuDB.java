package ehu.isad.controllers.db;

import ehu.isad.model.Herrialdea;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class hautatuDB {

    private static hautatuDB instantzia = new hautatuDB();

    public static hautatuDB getInstantzia(){
        return instantzia;
    }

    private hautatuDB (){}


    //Important methods

    public List<Herrialdea>lortuHerrialdeak(){
        List<Herrialdea> emaitza = new ArrayList<>();
        DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
        String query = "select * from Herrialde";
        //select * from Herrialde, ParteHartzea where urtea == strftime('%Y','now') and etorrikoDa = 'Bai'
        //Datu basea hutsik dagoenez, guztiak hartu ditut. Baina egokia urtea eta partaidetza kontuan hartzea da.
        ResultSet rs = dbkud.execSQL(query);

        try {
            while (rs.next()) {
                String izena = rs.getString("izena");
                String bandera = rs.getString("bandera");
                String tv = rs.getString("tv");
                Herrialdea herrialdea = new Herrialdea(izena, bandera, tv);
                emaitza.add(herrialdea);
            }
        }catch (SQLException e){
            System.err.println(e);
        }


        return emaitza;
    }
    public int botoKopurua(Herrialdea herrialdea) throws SQLException {
            DBKudeatzaile dbkud = DBKudeatzaile.getInstantzia();
            String query = "select sum(puntuak) as kopurua from Bozkaketa where Bozkaketa.bozkatuDu = '"+herrialdea.toString()+"'";
            //select * from Herrialde, ParteHartzea where urtea == strftime('%Y','now') and etorrikoDa = 'Bai'
            ResultSet rs = dbkud.execSQL(query);
            int a;
            if (rs.next()) {
            return rs.getInt("kopurua");
        }
        return 0;


    }
    }

