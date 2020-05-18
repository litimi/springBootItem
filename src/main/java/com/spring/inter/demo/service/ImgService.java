package com.spring.inter.demo.service;

import com.spring.inter.demo.bean.Img;
import com.spring.inter.demo.repository.JpaImgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgService implements IImgService {

    @Autowired
    JpaImgRepository imgRepository;

    @Override
    public Img add(Img img, String path) {
        img.setUrl(path);
        return imgRepository.save(img);
    }
}
