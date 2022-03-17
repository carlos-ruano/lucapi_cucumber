#Author: equipo 3
#Keywords Summary :
#Feature: List of scenarios.
@tag
Feature: Hacer un crud de  empleados
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
    
