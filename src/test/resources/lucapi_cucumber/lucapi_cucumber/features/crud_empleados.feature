#Author: equipo 3
#Keywords Summary :
#Feature: List of scenarios.
@tag
Feature: Hacer un post put y delete de  empleados
  Yo como administrador quiero poder consultar un listado de empleados

  @put1
  Scenario: Inserto un empleado a la lista
    Given Usuario que selecciona POST a la api
    And Mete en el body un JSON con los datos del empleado
    When Solicita la peticion POST a empleados
    Then Recibo un HTTP response code de doscientos
    And El numero de empleados se ha modificado en mas uno

  @put1
  Scenario: Inserto un empleado duplicado a la lista
    Given Usuario que selecciona POST a la api con datos duplicados
    And Mete en el body un JSON con los datos del empleado duplicado
    When Solicita la peticion POST a empleados duplicado
    Then Recibo un HTTP response code de cuatrocerocuatro
    And Recibe un json vacio
    
  @put1
  Scenario: Inserto un empleado a la lista con uri mal
    Given Usuario que selecciona POST a la api con url mal
    And Mete en el body un JSON con los datos del empleado x
    When Solicita la peticion POST a empleado
    Then Recibo un HTTP response code de cuatrocerocuatro por url mal
    And Recibe json vacio
    
  @delete1
  Scenario: delete un empleado de la lista 
    Given Usuario que selecciona delete a la api
    When Solicita la peticion delete a empleados
    Then Recibo un HTTP response code de dos cero cero
    And el numero se decrementa de uno
    
 @delete1
  Scenario: delete un empleado de la lista con uri mal 
    Given Usuario que selecciona delete a la api con uri mal
    When Solicita la peticion delete a empleados con uri mal
    Then Recibo un HTTP response code de cuatro cero cuatro por uri mal
    And el numero no cambia
    
  @delete1
  Scenario: delete un empleado que no existe en la lista 
    Given Usuario que selecciona delete a la api con usuario que no existe
    When Solicita la peticion delete a empleados con que no existe
    Then Recibo un HTTP response code de cuatro cero cuatro porque el usuario no existe
    And el numero no se decrementa
    
  @update1
  	Scenario: update un empleado de la lista 
    Given Usuario que selecciona update a la api
    And   Entra los datos a modificar 
    When  Solicita la peticion update a empleados 
    Then  Recibo un HTTP rescode de dos cero cero
    Then  Recibo una respuesta con el json con el nombre cambiado
    
  @update1
		Scenario: actualizo un empleado no existente la lista 
	  Given Usuario que selecciona put a la api
	  And   Mete en el body un json de los datos del empleado 
	  When  Solicita la peticion put a empleados/numero 
	  Then  Recibo un HTTP status code de cuatro cero cuatro
	  Then  Recibo una respuesta con un json vacio
	  
  @update1
		Scenario: fallo al actualizar un empleado porque el url esta mal
	  Given Usuario que selecciona put a la api con url mal
	  And   Mete dentro del body un json de los datos del empleado 
	  When  Solicita la peticion put a empleado/numero 
	  Then  Recibo un HTTP status code de cuatro cero cuatro porque el url esta mal
	  Then  Recibo una res con un json vacio

    
  
    
