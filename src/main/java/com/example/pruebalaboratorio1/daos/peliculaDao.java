package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.Genero;
import com.example.pruebalaboratorio1.beans.Pelicula;
import com.example.pruebalaboratorio1.beans.Streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<Pelicula> listarPeliculas() {

        ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

        try
                (Connection conn = this.getConnection();
            Statement stmt = conn.createStatement())

        { String sql = "SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                    "(SELECT * FROM PELICULA ) AS A \n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM GENERO) AS B\n" +
                    "ON A.IDGENERO = B.IDGENERO\n" +
                    "INNER JOIN \n" +
                    "(SELECT * FROM STREAMING) AS C\n" +
                    "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                    "ORDER BY RATING DESC , BOXOFFICE DESC";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Pelicula movie = new Pelicula();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
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

                //Creación del objeto genero
                Genero genero = new Genero();
                int idGenero =rs.getInt("idGenero");
                String nombregenero = rs.getString("nombre");
                genero.setIdGenero(idGenero);
                genero.setNombre(nombregenero);
                movie.setGenero(genero);

                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);

                //Creación del objeto streaming
                Streaming streaming= new Streaming();
                String nombreServicio = rs.getString("nombreServicio");
                int idStreaming = rs.getInt("idStreaming");
                streaming.setIdStreaming(idStreaming);
                streaming.setNombreServicio(nombreServicio);

                movie.setStreaming(streaming);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);

                //Validación de borrado
                String duracionsinMin = duracion.replace("min","");
                int duracionInt =Integer.parseInt(duracionsinMin);
                movie.setValidador(validarBorrado(duracionInt, oscar));


                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<Pelicula> listarPeliculasFiltradas(int idGenero) {

        ArrayList<Pelicula> listaPeliculasFiltradas= new ArrayList<>();

        return listaPeliculasFiltradas;
    }

    public void crearPelicula( ){

    }

@Override
    public boolean validarBorrado(int duracion ,boolean oscar) {
        return duracion>180 && !oscar;
    }
    public void borrarPelicula(int idPelicula) {
        detallesDao daoDetalles =new detallesDao();
        Pelicula movie = daoDetalles.obtenerPelicula(idPelicula);

        if (movie.getValidador()){
            String sql = "delete from pelicula where idPelicula = ?";
            try (Connection conn =this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setInt(1,idPelicula);
                pstmt.executeUpdate();

            }catch(SQLException e){throw new RuntimeException(e);}

        }

    }


    }

