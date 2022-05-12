package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;


public class dbConnect  {




    public static void main(String[] args) {

            createID();
        // createAsiakas("99", "method Testi", "Method Testi", "2", "3","020", "00220");
        // createMökki("12", "13", "00330", "testiMökki", "mökkitie", 200, "testi mökki", 100, "Oikea mökki");
        //  createVaraus("70", "31", "21", "2022-07-07", "2022-12-22", "3333-03-23", "1999-09-09");
        // createLasku("12", "31", 300, 14);


    }

    public static void createAsiakas(String pswrd, int asiakas_id, String etunimi, String sukunimi, String lahiosoite, String email, String puh, String postinro) {

        String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
        String username = "postgres";
        String password = "kuolema";
        String tietokanta = "asiakkaat";

        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to DB");

            String insertIntoSql = "INSERT INTO " + tietokanta + "(asiakas_id, etunimi, sukunimi, lahiosoite, email, puhelinnro, postinro)" +
                    " VALUES ('" + asiakas_id + "', '" + etunimi + "', '" + sukunimi+ "', '" +lahiosoite+ "', '" + email + "', '" + puh+ "', '" + postinro +"')";


            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(insertIntoSql);
            if (rows > 0)
                System.out.println("Inserted succesfully into: " + tietokanta);

            connection.close();

        } catch (SQLException e) {
            System.out.println("!!! Error in connecting to sql !!!");
            e.printStackTrace();
        }
    }
    public static void createMökki(int mokki_id, int alue_id, String postinro, String mokkiNimi, String katuosoite, double hinta, String kuvaus, int henkilomaara, String varustelu){
        String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
        String username = "postgres";
        String password = "kuolema";
        String tietokanta = "kohteet";

        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to DB");

            String insertIntoSql = "INSERT INTO " + tietokanta + "(mokki_id, alue_id, postinro, mokkiNimi, katuosoite, hinta, kuvaus, henkilomaara, varustelu)" +
                    " VALUES ('" + mokki_id + "', '" + alue_id + "', '" + postinro+ "', '" +mokkiNimi+ "', '" + katuosoite + "', '" + hinta + "', '" + kuvaus +"', '" +henkilomaara + "', '" + varustelu +"' )";


            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(insertIntoSql);
            if (rows > 0)
                System.out.println("Insert success into: " + tietokanta);

            connection.close();

        } catch (SQLException e) {
            System.out.println("!!! Error in connecting to sql !!!");
            e.printStackTrace();
        }
    }

    public static void createVaraus(int varaus_id, int asiakas_id, String mokki_mokki_id, String varattu_alkupvm, String varattu_loppupvm){
        String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
        String username = "postgres";
        String password = "kuolema";
        String tietokanta = "varaukset";

        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to DB");

            String insertIntoSql = "INSERT INTO " + tietokanta + "(varaus_id, asiakas_id, mokki_mokki_id, varattu_pvm, vahvistus_pvm, varattu_alkupvm, varattu_loppupvm)" +
                    " VALUES ('" + varaus_id + "', '" + asiakas_id + "', '" + mokki_mokki_id + "', '" + varattu_alkupvm + "', '" + varattu_loppupvm +"')";


            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(insertIntoSql);
            if (rows > 0)
                System.out.println("Insert success into: " + tietokanta);

            connection.close();

        } catch (SQLException e) {
            System.out.println("!!! Error in connecting to sql !!!");
            e.printStackTrace();
        }
    }



    public static void createLasku(int lasku_id, int varaus_id, double summa, double alv){
        String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
        String username = "postgres";
        String password = "kuolema";
        String tietokanta = "laskut";

        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to DB");

            String insertIntoSql = "INSERT INTO " + tietokanta + "(varaus_id, lasku_id, summa, alv )" +
                    " VALUES ('" + varaus_id + "', '" + lasku_id + "', '" +summa+ "', '" + alv + "')";


            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(insertIntoSql);
            if (rows > 0)
                System.out.println("Insert success into: " + tietokanta);

            connection.close();

        } catch (SQLException e) {
            System.out.println("!!! ERROR !!!");
            e.printStackTrace();
        }
    }

    public static int  createID(){
        int x = 0;
        String id = "0";
        while( x < 7 ){
            int randomNum = ThreadLocalRandom.current().nextInt(0, 9 + 1);
            id = id + (String.valueOf(randomNum));
            x++;
        }
        int idInt = Integer.parseInt(id);
        return idInt;
    }
        public static int getAsiakasID(){
            String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
            String username = "postgres";
            String password = "kuolema";
            int id = 0;


            try {
                Connection connection = DriverManager.getConnection(dbURL, username, password);
                System.out.println("Connected to DB");
                // int asiakas_id = get asiakas id
                return id;



        } catch (SQLException e) {
        System.out.println("!!! ERROR !!!");
        e.printStackTrace();
    }
    return id;}



    public static ObservableList loadFromDb(String hakuAlue, String hakuSanat) {
        String dbURL = "jdbc:postgresql://localhost:5432/VillageNewbiesDB";
        String username = "postgres";
        String password = "kuolema";
        String tietokanta = "laskut";


        try {
            Connection connection = DriverManager.getConnection(dbURL, username, password);
            System.out.println("Connected to DB");
            PreparedStatement pst = null;

            if (hakuAlue.equals("asiakas")){
                pst = connection.prepareStatement("SELECT * FROM asiakkaat");
                ResultSet rs = pst.executeQuery();
                hakuAsiakas haku = new hakuAsiakas("moi", "testi", "040666", "test@");
                System.out.println(haku.getNimi());

            }
            else if (hakuAlue.equals("varaus")){
                pst = connection.prepareStatement("SELECT * FROM 'varaukset'");
                ResultSet rs = pst.executeQuery();

            }
            else if (hakuAlue.equals("mökki")){
                pst = connection.prepareStatement("SELECT * FROM 'kohteet'");
                ResultSet rs = pst.executeQuery();
            }
            else if (hakuAlue.equals("lasku")){
                pst = connection.prepareStatement("SELECT * FROM 'laskut'");
                ResultSet rs = pst.executeQuery();


            }


            connection.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static class asiakkaatHaku {
    }

    private static class hakuAsiakas {
        String nimi;
        String osoite;
        String puh;
        String email;

        public hakuAsiakas(String nimi, String osoite, String puh, String email) {
            this.nimi = nimi;
            this.osoite = osoite;
            this.puh = puh;
            this.email = email;
        }

        public String getNimi() {
            return nimi;
        }

        public void setNimi(String nimi) {
            this.nimi = nimi;
        }

        public String getOsoite() {
            return osoite;
        }

        public void setOsoite(String osoite) {
            this.osoite = osoite;
        }

        public String getPuh() {
            return puh;
        }

        public void setPuh(String puh) {
            this.puh = puh;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}












