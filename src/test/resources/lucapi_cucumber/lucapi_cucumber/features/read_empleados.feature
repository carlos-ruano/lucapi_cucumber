#Author: equipo 3
#Keywords Summary :
#Feature: List of scenarios.
@tag
Feature: Hacer un read de empleados
  Yo como administrador quiero poder consultar un listado de empleados

  @getAll
  Scenario: Cargo la lista completa de empleados
    Given Usuario que selecciona get a la api empleados
    When Solicita la peticion get a empleados
    Then Recibo un HTTP response code doscientos
    And Devuelve un json con diez empleados

  @getAll
  Scenario: Fallo al cargar la lista de empleados porque la url esta mal
    Given Usuario que selecciona GET a la api/empleado
    When Solicita la peticion get a empleado sin ese final
    Then Recibo un HTTP response code cuatrocerocuatro
    And Devuelve un json con una bad request
    
  @get1
  Scenario: Cargo a un empleado de la lista
    Given Usuario que selecciona GET a un empleado en la api
    When Solicita la peticion GET a empleados/uno
    Then Recibo un HTTP rescode doscientos 
    And Devuelve un json con un empleado
   
  @get1
  Scenario: Fallo al cargar al empleado porque la url esta mal
    Given Usuario que selecciona GET a un empleado en la api url mal
    When Solicita la peticion GET a empleado/uno
    Then Recibo un HTTP rescode cuatrocerocuatro 
    And Devuelve un json vacio

	@get1
  Scenario: Fallo al cargar al empleado porque no existe empleado
    Given Usuario que selecciona GET a un empleado no existente
    When Solicita la peticion GET a empleados/cero
    Then Recibo un HTTP respcode cuatrocerocuatro 
    And Devuelve un json muy vacio
    
  @nombre1
		Scenario: filtro por nombre que existe
	  Given Usuario que selecciona get a la api con nombre como param 
	  When  Solicita la peticion get a empleados/nombre 
	  Then  Recibo un HTTP status code de Doscientos
	  Then  Devuelve un json con los datos del empleado
	  
  @nombre1
		Scenario: filtro por nombre con url mal
	  Given Usuario que selecciona get a la api con nombre y url mal
	  When  Solicita la peticion get a empleado/nombre 
	  Then  Recibo un HTTP statusCode de cuatro cero cuatro
	  Then  Devuelve un json vacio porque el url esta mal
	  
  @nombre1
		Scenario: filtro por nombre que no existe
	  Given Usuario que selecciona get a la api con nombre que no existe
	  When  Solicita la peticion get a empleado/nombre que no existe
	  Then  Recibo un HTTP statusCode de cuatro cero cuatro porque el nombre no existe
	  Then  Devuelve un json vacio porque el empleado no existe
	  
  @ciudad1
		Scenario: filtro por ciudad que existe
	  Given Usuario que selecciona get a la api con ciudad
	  When  Solicita la peticion get a empleados?ciudad 
	  Then  Recibo un HTTP statusCode de dos cero cero
	  Then  Devuelve un json con los empleados que viven en esta ciudad
	  
  @ciudad1
		Scenario: filtro por ciudad con url mal
	  Given Usuario que selecciona get a la api con ciudad con url mal
	  When  Solicita la peticion get a empleado?ciudad con url mal
	  Then  Recibo un HTTP statusCode de cuatro cero cuatro porque url esta mal
	  Then  Devuelve json vacio porque el url esta mal
	  
  @ciudad1
		Scenario: filtro por ciudad que no existe
	  Given Usuario que selecciona get a la api con ciudad que no existe
	  When  Solicita la peticion get a empleado?ciudad que no existe
	  Then  Recibo un HTTP statusCode de cuatro cero cuatro porque la ciudad no existe
	  Then  Devuelve un json vacio porque la ciudad no existe
	  
  @error
  Scenario: filtro por atributo que no existe
	  Given Usuario que selecciona get a la api con un atributo que no existe
	  When  Solicita la peticion get a empleado?atributo que no existe
	  Then  Recibo un HTTP statusCode de cuatro cero cuatro porque el atributo no existe
	  Then  Devuelve un json vacio porque el atributo no existe
  
