Feature: Athena Login

  Scenario Outline: Enters valid athena Username and Password
    Given Open Chrome browser
    When Enters username "p-avenkatesan" and password "Yosi2607"
    Then Click Login
    Then Department
    Then AthenaOne PatientReg

