package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.Genero;
import com.example.pruebalaboratorio1.beans.Pelicula;
import com.example.pruebalaboratorio1.beans.Streaming;

import javax.swing.plaf.basic.BasicMenuUI;
import java.sql.*;

public class detallesDao {

    public Pelicula obtenerPelicula(int idPelicula) {

        Pelicula movie = new Pelicula();
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

            String sql = "SELECT A.*, B.NOMBRE FROM \n" +
                    "(SELECT * FROM PELICULA WHERE IDPELICULA = \n" +
                     idPelicula +
                    ") AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO";
            // hacer el join con el genero y pedir que se haga por rating desc
            // agregar buscador

            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {

                int id = rs.getInt(1);
                movie.setIdPelicula(id);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);

                Genero genero = new Genero();
                String nombregenero = rs.getString("nombre");
                int idGenero =rs.getInt("idGenero");
                genero.setNombre(nombregenero);
                genero.setIdGenero(idGenero);
                movie.setGenero(genero);

                //Creación del objeto streaming
                Streaming streaming= new Streaming();
                String nombreServicio = rs.getString("nombreServicio");
                int idStreaming = rs.getInt("idStreaming");
                streaming.setIdStreaming(idStreaming);
                streaming.setNombreServicio(nombreServicio);

                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);

                movie.setPremioOscar(rs.getBoolean("premioOscar"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return movie;
    }
}
