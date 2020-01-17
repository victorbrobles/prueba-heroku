package urjc.isi.entidades;

import java.util.Objects;
import urjc.isi.exceptions.*;
import java.util.StringTokenizer;

import com.google.gson.JsonObject;

//Solamente es la definición de la tabla
//sus campos y como trabajar con ellos

public class Peliculas {


	private String idpelicula;
	private String titulo;
	private int año;
	private int duracion;
	private int calificacion;
	private double rating;
	private int nvotos;

	public Peliculas(){}

	public Peliculas(String idpelicula, String titulo, int año,
                    int duracion, int calificacion, double rating, int nvotos){
	    this.setIdPelicula(idpelicula);
	    this.setTitulo(titulo);
	    this.setAño(año);
	    this.setDuracion(duracion);
	    this.setCalificacion(calificacion);
	    this.setRating(rating);
	    this.setNVotos(nvotos);
	}

	public Peliculas(String line){// Se tokeniza la linea
		StringTokenizer tokenizer = new StringTokenizer(line,"\t");
		this.setIdPelicula(tokenizer.nextToken());
		this.setTitulo(tokenizer.nextToken());
		this.setAño(Integer.valueOf(tokenizer.nextToken()));
		this.setDuracion(Integer.valueOf(tokenizer.nextToken()));
		this.setCalificacion(Integer.valueOf(tokenizer.nextToken()));
		this.setRating(Double.valueOf(tokenizer.nextToken()));
		this.setNVotos(Integer.valueOf(tokenizer.nextToken()));
	}

	// Setter Methods
	public void setIdPelicula(String idpelicula){
		if(idpelicula == null){
			throw new InvalidParameter();
	    }
		this.idpelicula = idpelicula;
	}

	public void setTitulo(String titulo){
		if(titulo == null){
			throw new InvalidParameter();
		}
		this.titulo = titulo;
	}

	public void setAño(int año){
		if(año <= 1895){ //Año de creacion del cine
			throw new InvalidParameter();
		}
		this.año = año;
	}

	public void setDuracion(int duracion){
		if(duracion > 0){
			this.duracion = duracion;
		}else{
			throw new InvalidParameter();
		}

	}

	public void setCalificacion(int calificacion) {
		if(calificacion < 0 || calificacion > 1) {
				throw new InvalidParameter();
		}
		this.calificacion = calificacion;
	}
	public void setRating(double rating){
		if(rating < 0 || rating > 10){
			throw new InvalidParameter();
		}
		this.rating = rating;
	}

	public void setNVotos(int nvotos){
		if(nvotos < 0){
			throw new InvalidParameter();
		}
		this.nvotos = nvotos;
	}

	// Getter Methods
	public String getIdPelicula() {
		return idpelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getAño(){
		return año;
	}

	public int getDuracion(){
		return duracion;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public double getRating(){
		return rating;
	}

	public int getNVotos(){
		return nvotos;
	}

	// Overrided Methods
	@Override
	public boolean equals(Object other) {
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Peliculas)) return false;

	    Peliculas otherP = (Peliculas)other;

	    return Objects.equals(this.idpelicula, otherP.idpelicula) &&
	    		Objects.equals(this.titulo,otherP.titulo) &&
	              (this.año == otherP.año) &&
	              (this.duracion == otherP.duracion) &&
	              (this.calificacion==otherP.calificacion)&&
	              (this.rating == otherP.rating) &&
	              (this.nvotos == otherP.nvotos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idpelicula,titulo,año,duracion,calificacion,rating,nvotos);
	}

	@Override
	public String toString(){
		return "Id Película: "+getIdPelicula()+"\tTitulo: " + getTitulo() +
				" (" + getAño() + ") " + "\tDuracion: "+getDuracion() + "\tCalificacion: " + getCalificacion() +
				"\tRating: "+getRating()+"\tNumero de Votos: "+getNVotos();
	}

	public String toHTMLString() { //Método necesario para una buena respuesta en el servidor
		return "Id Película: "+getIdPelicula()+"&emsp; Titulo: " + getTitulo() +
				" (" + getAño() + ") " + "&emsp; Duracion: "+getDuracion()+
				"&emsp; Calificacion: "+getCalificacion()+"&emsp; Rating: "+getRating()+
				"&emsp; Numero de Votos: "+getNVotos();
	}

	public JsonObject toJSONObject () {
		JsonObject peliculaJSON = new JsonObject();
		peliculaJSON.addProperty("Id", getIdPelicula());
		peliculaJSON.addProperty("Titulo", getTitulo());
		peliculaJSON.addProperty("Año", getAño());
		peliculaJSON.addProperty("Duracion", getDuracion());
		peliculaJSON.addProperty("Calificacion", getCalificacion());
		peliculaJSON.addProperty("Rating", getRating());
		peliculaJSON.addProperty("Numero de votos", getNVotos());
		return peliculaJSON;
	}

}
