package com.example.RegisterDemo;

public class Msg {
    public static final int TYPE_RECEIVER = 0;

    public static final int TYPE_SENT = 1;

    public static final int TYPE_IMG_SENT = 3;

    public static final int TYPE_IMG_RECEIVED = 2;

    private String content;

    private int type;

    private int imageId;

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public Msg(int imageId, int type) {
        this.type = type;
        this.imageId = imageId;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }

    public int getImageId() {
        return imageId;
    }
}
