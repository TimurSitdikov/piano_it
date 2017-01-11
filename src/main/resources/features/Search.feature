Feature: Feature to test search mechanism.

  @SearchSimpleCompanySearch
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

#  @SearchCompanyByLocationSearch
#  Scenario Outline: Test for simple search by company location
#    Given I open main page
#    When I search for company:
#      | location   |
#      | <location> |
#    Then I check that companies in result are from: <location>
#
#
#    Examples:
#      | location |