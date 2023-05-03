/*
 *
 *  JSP Assignment 2
 *  Wyatt LeMaster
 *  5/2/2023
 *  Class represents a Topic object
 *
 *
 */


package models;

public class TopicModel {
    private int topic_id;
    private String topic_name;

    public TopicModel(int topic_id, String topic_name) {
        this.topic_id = topic_id;
        this.topic_name = topic_name;
    }


    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }


}
