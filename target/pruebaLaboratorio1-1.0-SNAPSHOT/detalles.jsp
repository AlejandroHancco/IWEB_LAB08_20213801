<%@page import="java.util.ArrayList"%>
<%@page import="com.example.pruebalaboratorio1.beans.pelicula"%>
<%@page import="java.text.NumberFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    pelicula movie = (pelicula) request.getAttribute("pelicula");
    String searchTerm = request.getParameter("searchTerm");
    NumberFormat formatter = NumberFormat.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%=movie.getTitulo()%></title>
</head>
<body>

<h1><%=movie.getTitulo()%></h1>

<table border="1">
    <tr>
        <th>idPelicula</th>
        <td><%=movie.getIdPelicula()%></td> <!-- Ejemplo de idPelicula -->
    </tr>
    <tr>
        <th>Director</th>
        <td><%=movie.getDirector()%></td> <!-- Ejemplo de Director -->
    </tr>
    <tr>
        <th>Año Publicacion</th>
        <td><%=movie.getAnoPublicacion()%></td> <!-- Ejemplo de Año Publicacion -->
    </tr>
    <tr>
        <th>Rating</th>
        <td><%=movie.getRating()%>/10</td> <!-- Ejemplo de Rating -->
    </tr>
    <tr>
        <th>BoxOffice</th>
        <td>$<%=formatter.format(movie.getBoxOffice())%></td> <!-- Ejemplo de BoxOffice formateado -->
    </tr>
    <tr>
        <th>Genero</th>
        <td><%=movie.getGenero()%></td> <!-- Ejemplo de Genero -->
    </tr>
    <tr>
        <th>Actores</th>
        <td><a href="listaActores?idPelicula=<%= movie.getIdPelicula() %>">Ver Actores</a></td> <!-- Ejemplo de Genero -->
    </tr>
</table>

</body>
</html>
