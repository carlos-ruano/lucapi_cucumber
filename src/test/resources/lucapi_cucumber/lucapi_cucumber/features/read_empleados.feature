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
