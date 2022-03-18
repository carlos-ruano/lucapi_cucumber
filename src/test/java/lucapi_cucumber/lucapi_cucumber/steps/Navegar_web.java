package lucapi_cucumber.lucapi_cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.assertj.core.api.Assertions.assertThat;

public class Navegar_web {
	private static final String BASE_URL = "https://web-static.ivan-aa.repl.co";
	private static Response res;
	boolean estado;
	
	@Given("usuario que quiere entrar en la pagina home")
	public void usuario_que_quiere_entrar_en_la_pagina_home() {
	    RestAssured.baseURI = BASE_URL;
	}
	@When("click en el enlace")
	public void click_en_el_enlace() {
		 RequestSpecification request = RestAssured.given();
		 res = request.get("/index.html");
		 estado =res.getBody().asString().contains("<h1>Las Voces del Cambio</h1>");		
	}
	@Then("codigo respuesta doscientos")
	public void codigo_respuesta_doscientos() {
		assertThat(res.getStatusCode()).isEqualTo(200);
	}
	@Then("Tiene el contenido las voces del cambio")
	public void tiene_el_contenido_las_voces_del_cambio() {
	    assertThat(estado).isEqualTo(true);
	}
	
	// scenario 2
	
	@Given("usuario que quiere entrar en la pagina contacto")
	public void usuario_que_quiere_entrar_en_la_pagina_contacto() {
	    RestAssured.baseURI = BASE_URL;
	}
	@When("click en el enlace de la pagina contacto")
	public void click_en_el_enlace_de_la_pagina_contacto() {
		 RequestSpecification request = RestAssured.given();
		 res = request.get("/contacto.html");
		 estado =res.getBody().asString().contains("<h1>Contactanos</h1>");
	}
	@Then("devuelve un codigo respuesta doscientos")
	public void devuelve_un_codigo_respuesta_doscientos() {
		assertThat(res.getStatusCode()).isEqualTo(200);
	}
	@Then("Tiene el contenido Contactanos")
	public void tiene_el_contenido_contactanos() {
		assertThat(estado).isEqualTo(true);
	}
	
	// Scenario 3
	
	@Given("usuario que quiere entrar en la pagina servicios")
	public void usuario_que_quiere_entrar_en_la_pagina_servicios() {
	    RestAssured.baseURI = BASE_URL;
	}
	@When("click en el enlace de la pagina servicios")
	public void click_en_el_enlace_de_la_pagina_servicios() {
		 RequestSpecification request = RestAssured.given();
		 res = request.get("/servicios.html");
		 estado =res.getBody().asString().contains("<h1>Servicios</h1>");
	}
	@Then("devuelve  codigo respuesta doscientos")
	public void devuelve_codigo_respuesta_doscientos() {
		assertThat(res.getStatusCode()).isEqualTo(200);
	}
	@Then("Tiene el contenido servicios")
	public void tiene_el_contenido_servicios() {
		assertThat(estado).isEqualTo(true);	
	}
	
	// Scenario 3
	
	@Given("usuario que quiere entrar en la pagina equipo")
	public void usuario_que_quiere_entrar_en_la_pagina_equipo() {
		RestAssured.baseURI = BASE_URL;
		
	}
	@When("click en el enlace de la pagina equipo")
	public void click_en_el_enlace_de_la_pagina_equipo() {
		RequestSpecification request = RestAssured.given();
		res = request.get("/equipo.html");
		estado =res.getBody().asString().contains("<h1>Equipo</h1>");
	}
	@Then("devuelve codigo respuesta doscientos en equipo")
	public void devuelve_codigo_respuesta_doscientos_en_equipo() {
		assertThat(res.getStatusCode()).isEqualTo(200);
	}
	@Then("Tiene el contenido equipo")
	public void tiene_el_contenido_equipo() {
		assertThat(estado).isEqualTo(true);	
	}

}
