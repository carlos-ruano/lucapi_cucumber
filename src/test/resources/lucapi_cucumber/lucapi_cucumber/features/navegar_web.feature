#Author: your.email@your.domain.com

@tag
Feature: Navegar por la web

  @tag1
  Scenario: entro en la pagina home
  
  	Given usuario que quiere entrar en la pagina home
  	When  click en el enlace
  	Then  codigo respuesta doscientos 
  	And   Tiene el contenido las voces del cambio
  	
	@tag2
  Scenario: entro en la pagina contacto
  
  	Given usuario que quiere entrar en la pagina contacto
  	When  click en el enlace de la pagina contacto
  	Then  devuelve un codigo respuesta doscientos 
  	And   Tiene el contenido Contactanos
  	
	@tag3
  Scenario: entro en la pagina servicios
  
  	Given usuario que quiere entrar en la pagina servicios
  	When  click en el enlace de la pagina servicios
  	Then  devuelve  codigo respuesta doscientos 
  	And   Tiene el contenido servicios
	
	@tag4
  Scenario: entro en la pagina Equipo
  
  	Given usuario que quiere entrar en la pagina equipo
  	When  click en el enlace de la pagina equipo
  	Then  devuelve codigo respuesta doscientos en equipo
  	And   Tiene el contenido equipo
    

  
