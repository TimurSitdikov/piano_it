package com.piano.it.bo;

public class Company {

    private String name;
    private String locations;
    private String tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocations() {
        return locations;
    }

    public void addTag(String tag) {
        if (tags == null || tags.isEmpty()) {
            tags = tag;
        } else {
            tags = tags + "," + tag;
        }
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        if (locations != null ? !locations.equals(company.locations) : company.locations != null) return false;
        return tags != null ? tags.equals(company.tags) : company.tags == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (locations != null ? locations.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name: '" + name + '\'' +
                ", locations: '" + locations + '\'' +
                ", tags: " + tags +
                '}';
    }
}
