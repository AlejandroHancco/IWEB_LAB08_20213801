package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.Actor;

import java.sql.*;
import java.util.ArrayList;

public class actorDao{

    public ArrayList<Actor> listarActores(int idPelicula) {

        ArrayList<Actor> listaActores = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        String username = "root";
        String password = "root";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            String sql = "SELECT A.*\n" +
                    "FROM \n" +
                    "(SELECT * FROM ACTOR ) AS A\n" +
                    "INNER JOIN\n" +
                    "(SELECT * FROM PROTAGONISTAS WHERE IDPELICULA = \n" +
                    idPelicula +
                    ") AS B\n" +
                    "on a.idactor = b.idactor\n";
            // hacer el join con el genero y pedir que se haga por rating desc
            // agregar buscador

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Actor actuador = new Actor();
                int idActor = rs.getInt(1);
                actuador.setIdActor(idActor);
                String nombre = rs.getString("nombre");
                actuador.setNombre(nombre);
                String apellido = rs.getString("apellido");
                actuador.setApellido(apellido);
                int anoNacimiento = rs.getInt("anoNacimiento");
                actuador.setAnoNacimiento(anoNacimiento);
                boolean oscar = rs.getBoolean("premioOscar");
                actuador.setPremioOscar(oscar);


                listaActores.add(actuador);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaActores;
    }
}
