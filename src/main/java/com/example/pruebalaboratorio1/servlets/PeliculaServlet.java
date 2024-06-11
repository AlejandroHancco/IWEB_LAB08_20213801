package com.example.pruebalaboratorio1.servlets;

import com.example.pruebalaboratorio1.beans.Pelicula;
import com.example.pruebalaboratorio1.daos.listasDao;
import com.example.pruebalaboratorio1.daos.peliculaDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "pelicula-servlet", value = "/listaPeliculas")
public class PeliculaServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        String action = request.getParameter("action");
        peliculaDao peliculaDao = new peliculaDao();
        listasDao listaDao = new listasDao();
        //ArrayList<genero> listarGeneros = listaDao.listarGeneros();
        //ArrayList<streaming> listarStraming = listaDao.listarStraming();

        switch (action) {
            case "listar":

                //request.setAttribute("listarGeneros", listarGeneros);
                //request.setAttribute("listarStraming", listarStraming);

                ArrayList<Pelicula> listaPeliculas = peliculaDao.listarPeliculas();
                request.setAttribute("listaPeliculas", listaPeliculas);

                RequestDispatcher view = request.getRequestDispatcher("listaPeliculas.jsp");
                view.forward(request,response);
                break;

            case "filtrar":
                //request.setAttribute("listarGeneros", listarGeneros);
                //request.setAttribute("listarStraming", listarStraming);
                break;

            case "crear":
                RequestDispatcher view2 = request.getRequestDispatcher("crearPelicula.jsp");
                view2.forward(request,response);
                break;


            case "borrar":
                //int idPelicula = Integer.parseInt(request.getParameter("idPelicula"));
                //peliculaDao.borrarPelicula(idPelicula);
                //sendRedirect(request.getContextPath()+"/listaPeliculas?action=listar");
                break;

        }


    }


}
