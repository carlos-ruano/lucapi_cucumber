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
    
    
