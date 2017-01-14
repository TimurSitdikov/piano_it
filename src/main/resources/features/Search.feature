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

  @SearchConcreteCompany
  Scenario Outline: Search for concrete company and check results
    Given I open main page
    When I search for company:
      | name   | locations   |
      | <name> | <locations> |
    Then I check that results contain company:
      | name   |
      | <name> |
    And I check that companies in result are from: <locations>

    Examples:
      | name   | locations |
      | yandex | moscow    |

  @SearchLocationProposals
  Scenario: Check that variants are available when user enters part of search query
    Given I open main page
    When I search for company but not click submit:
      | locations |
      | los       |
    Then I check that location proposals are available for: los

  @SearchByTags
  Scenario Outline: Test for company search by tags
    Given I open main page
    When I search for company:
      | tags   |
      | <tags> |
    Then I check that companies in result have tags: <tags>

    Examples:
      | tags                   |
      | java,selenium,cucumber |