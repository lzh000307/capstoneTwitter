package com.blog.pojo;

public class Censorship {
    private Integer id;
    private String word;

    @Override
    public String toString() {
        return "Censorship{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
