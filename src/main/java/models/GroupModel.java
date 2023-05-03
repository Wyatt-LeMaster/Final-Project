/**
 *
 *  JSP Assignment 2
 *  Emma Ingram
 *  5/3/2023
 *  Model for activity group
 *
 *
 */

package models;

public class GroupModel {
  public GroupModel(String name) {
    this.name = name;
  }
  private String name;

  public void setName(String name) { this.name = name; }

  public String getName() { return this.name; }

}
