Feature: Login
  Perform login on email and password are inputted

  @login-feature
  Scenario Outline: Input email and password in true format
    Given I am on login screen
    When I input username <username>
    And I input password "<password>"
    And I press Login button
    Then I should logon successfully

    Examples:
      | username | password  |
      | crazydog335 | venture |
