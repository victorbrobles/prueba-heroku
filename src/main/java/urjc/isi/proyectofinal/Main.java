package urjc.isi.proyectofinal;

import static spark.Spark.*;

import spark.Request;
import spark.Response;

import java.net.URISyntaxException;

import java.sql.*;

import urjc.isi.controladores.*;

public class Main {

	/**
	 * Este metodo devuelve la respuesta por defecto a cualquier endpoint no contemplado en la API REST y al /welcome
	 * @param request
	 * @param response
	 * @return Respuesta por defecto de la aplicación
	 * @throws ClassNotFoundException
	 * @throws URISyntaxException
	 */
	public static String defaultResponse(Request request, Response response) throws ClassNotFoundException, URISyntaxException {
       return "<form action='/pag_principal' method='post' enctype='multipart/form-data'>"
       		+ "<h1>Bienvenido a nuestra web de peliculas</h1>"
    		+ "<br>" + "<h2>ACTORES</h2>" + "<br>"
    		+ "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll>Imprimir todos los actores</a>"
       		+ "<p>Tambien puedes filtrar los actores por:</p>" 
       		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?id_act=x>id</a>" + "<br>"
       		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?name=x>nombre</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?fecha_nac=x>fecha de nacimiento</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?fecha_nac=x-x>intervalo fecha de nacimiento</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?fecha_muer=x>fecha de muerte</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/actores/selectAll?fecha_muer=x-x>intervalo fecha de muerte</a>" + "<br>"
    		+ "<p>Sustituyendo las x por los valores deseados</p>"
    		+ "<br>" + "<h2>PELICULAS</H2>" + "<br>"
    		+ "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll>Imprimir todas las peliculas</a>"
    		+ "<p>Tambien puedes filtrar las peliculas por:</p>" 
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?titulo=x>titulo</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?actor=x>actor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?director=x>director</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?guionista=x>guionista</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?year=x>año</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?year=x-x>intervalo de años</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?idioma=x>idioma</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?adultos=x>adultos(si/no)</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?duracion=>x>duracion mayor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?duracion=<x>duracion menor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?duracion=x>duracion</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?duracion=x-x>intervalo de duracion</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?rating=x-x>intervalo de puntuacion</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?rating=>x>puntuacion mayor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?rating=<x>puntuacion menor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/selectAll?rating=x>puntuacion</a>" + "<br>"
    		+ "<p>Sustiyendo las x por los valores deseados</p>"
    		+ "<br>" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/ranking>Diez peliculas mejor valoradas</a>"
    		+ "<p>Tambien puedes filtrar las peliculas por:</p>" 
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/ranking?actor=x>actor</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/ranking?director=x>director</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/ranking?guionista=x>guionista</a>" + "<br>"
    		+ "-->" + "<a href=https://prueba-grupo4.herokuapp.com/peliculas/ranking?genero=x>genero</a>" + "<br>"
    		+ "<br>" + "<h2>DIRECTORES</h2>" + "<br>"
    		+ "<a href=https://prueba-grupo4.herokuapp.com/directores/selectAll>Imprimir todos los directores</a>"
    		+ "<br>" + "<h2>GUIONISTAS</h2>" + "<br>"
    		+ "<a href=https://prueba-grupo4.herokuapp.com/guionistas/selectAll>Imprimir todos los guionistas</a>"
    		+ "<br>" + "<h2>GENEROS</h2>" + "<br>"
    		+ "<a href=https://prueba-grupo4.herokuapp.com/generos/selectAll>Imprimir todos los generos</a>"
    		+ "</form>";
    		  
    }

	/**
	 * Este metodo es un gestor de los endpoints asociados a cada una de las tablas de la base de datos
	 */
    public static void tables() {
    	path("peliculas",() -> {
        	PeliculasController Controller = new PeliculasController();
        	Controller.peliculasHandler();
        });
    	path("actores",()->{
    		ActoresController Controller = new ActoresController();
    		Controller.actoresHandler();
    	});
    	path("peliculasactores",()->{
    		PeliculasActoresController Controller = new PeliculasActoresController();
    		Controller.peliculasActoresHandler();
    	});
		path("directores", () ->{
			DirectoresController Controller = new DirectoresController();
			Controller.directoresHandler();
		});
		path("guionistas", () ->{
			GuionistasController Controller = new GuionistasController();
			Controller.guionistasHandler();
		});
		path("peliculasdirectores", () ->{
			PeliculasDirectoresController Controller = new PeliculasDirectoresController();
			Controller.peliculasDirectoresHandler();
		});
		path("peliculasguionistas", () ->{
			PeliculasGuionistasController Controller = new PeliculasGuionistasController();
			Controller.peliculasGuionistasHandler();
		});
    }

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
        port(getHerokuAssignedPort());
        get("/welcome", Main::defaultResponse);
        path("/",() -> {tables();});
        redirect.get("*", "/welcome");
    }

    /**
     * Este metodo asigna el puerto en el que va a correr la aplicación en Heroku
     * @return puerto en el que va a correr la aplicación
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
