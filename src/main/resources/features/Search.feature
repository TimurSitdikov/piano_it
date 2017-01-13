@Search
Feature: Feature to test search mechanism.

  @SearchCompanyByName
  Scenario Outline: Test for simple search by company name
    Given I open main page
    When I search for company:
      | name   |
      | <name> |
    Then I check that results contain company:
      | name   |
      | <name> |

    Examples:
      | name    |
      | trivago |

  @SearchCompanyByLocation
  Scenario: Test for simple search by company location
    Given I open main page
    When I search for company from:
      | locations |
      | Moscow    |
    Then I check that companies in result are from: Moscow,Moskva,Moscow Federal City,Moskau

  @SearchByTags
  Scenario Outline:
    Given I open main page
    When I search for company:
      | tags   |
      | <tags> |
    Then I check that companies in result have tags: <tags>

    Examples:
      | tags                   |
      | java,selenium,cucumber |


