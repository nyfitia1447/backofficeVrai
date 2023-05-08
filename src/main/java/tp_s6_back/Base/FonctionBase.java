package tp_s6_back.Base;

import java.sql.*;
import java.util.Vector;

public class FonctionBase {
    static int i=0,j=0;
    public static Connection connect() throws Exception {
        String url="jdbc:postgresql://containers-us-west-130.railway.app:5998/railway";
        String user="postgres";
        String passWord="w5udncxVTc3J6lbkNSEs";
        Connection connection;
        connection= DriverManager.getConnection(url,user,passWord);
        connection.setAutoCommit(true);
        return connection;
    }
    public static void modifBase(String sql) throws Exception{
        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
    public static void execute(String sql,Connection connnection) throws SQLException {
        try(Statement statement=connnection.createStatement()){
            statement.execute(sql);
        }
    }
    public static Vector<Object>[]select(String sql) throws Exception{
        try (Connection connection = connect (); Statement statement = connection.createStatement (); ResultSet resultSet = statement.executeQuery (sql)) {
            return getVectors(resultSet);
        }
    }

    public static Vector<Object>[]all(String sql,Connection connection) throws Exception{
        try ( Statement statement = connection.createStatement (); ResultSet resultSet = statement.executeQuery (sql)) {
            return getVectors(resultSet);
        }
    }

    private static Vector<Object>[] getVectors(ResultSet resultSet) throws SQLException {
        int e = 0;
        int size = resultSet.getMetaData ().getColumnCount ();
        Vector<Object>[] valiny = new Vector[size];
        for (i = 0; i < size; i++) {
            valiny[i] = new Vector<Object> (10000);
        }
        while (resultSet.next ()) {
            for (i = 0; i < size; i++) {
                valiny[i].add (e, resultSet.getObject (i + 1));
            }
            e++;
        }
        return valiny;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(connect());
    }
}

