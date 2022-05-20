package com.blog.controller.entity;

import com.blog.pojo.Tweet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class TweetForm extends Tweet {
    private MultipartFile img1;
    private MultipartFile img2;
    private MultipartFile img3;
    private MultipartFile img4;
    private MultipartFile img5;
    private MultipartFile img6;

    private MultipartFile[] imgs; // 图片数组
    public MultipartFile getImg1() {
        return img1;
    }

    public void setImg1(MultipartFile img1) {
        this.img1 = img1;
    }

    public MultipartFile getImg2() {
        return img2;
    }

    public void setImg2(MultipartFile img2) {
        this.img2 = img2;
    }

    public MultipartFile getImg3() {
        return img3;
    }

    public void setImg3(MultipartFile img3) {
        this.img3 = img3;
    }

    public MultipartFile getImg4() {
        return img4;
    }

    public void setImg4(MultipartFile img4) {
        this.img4 = img4;
    }

    public MultipartFile getImg5() {
        return img5;
    }

    public void setImg5(MultipartFile img5) {
        this.img5 = img5;
    }

    public MultipartFile getImg6() {
        return img6;
    }

    public void setImg6(MultipartFile img6) {
        this.img6 = img6;
    }

}
