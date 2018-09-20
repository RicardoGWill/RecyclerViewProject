package com.ricardogwill.recyclerviewproject;

public class ExampleItem {
    // This class goes along with: example_item.xml

    private int itemImageViewInt;
    private String textView1String;
    private String textView2String;

    public ExampleItem(int itemImageViewInt, String textView1String, String textView2String) {
        this.itemImageViewInt = itemImageViewInt;
        this.textView1String = textView1String;
        this.textView2String = textView2String;
    }

    public void changeText1(String text) {
        textView1String = text;
    }

    public int getItemImageViewInt() {
        return itemImageViewInt;
    }

    public String getTextView1String() {
        return textView1String;
    }

    public String getTextView2String() {
        return textView2String;
    }
}
