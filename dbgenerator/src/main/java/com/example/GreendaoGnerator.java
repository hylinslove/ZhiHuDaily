package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreendaoGnerator {

    public static void main(String[] args) throws Exception {

        Schema schema = new Schema(1, "com.greendao");

        addNote(schema);
        new DaoGenerator().generateAll(schema, "../");
    }

    /**
     * @param schema
     */
    private static void addNote(Schema schema) {

        Entity story = schema.addEntity("Story");
        story.addIdProperty();
        story.addIntProperty("newsId");
        story.addStringProperty("json");

        Entity news = schema.addEntity("News");
        news.addIdProperty();
        news.addStringProperty("json");

        Entity before = schema.addEntity("Before");
        before.addIdProperty();
        before.addStringProperty("date");
        before.addStringProperty("json");

    }

}
