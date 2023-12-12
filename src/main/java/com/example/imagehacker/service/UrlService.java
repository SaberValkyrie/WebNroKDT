package com.example.imagehacker.service;

import com.example.imagehacker.dao.ListUrlRepository;
import com.example.imagehacker.dao.URLRepository;
import com.example.imagehacker.dao.UserRepository;
import com.example.imagehacker.entity.ListUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UrlService {
    private final UserRepository userRepository;
    private final URLRepository urlRepository;
    private final ListUrlRepository listUrlRepository;
    @Autowired
    public UrlService(UserRepository userRepository, URLRepository urlRepository, ListUrlRepository listUrlRepository) {
        this.userRepository = userRepository;
        this.urlRepository = urlRepository;
        this.listUrlRepository = listUrlRepository;
    }

    public List<ListUrl> getAlllink(byte status) {
        return listUrlRepository.getallLink(status);
    }


    public void xoaLienKet(Long id) {
        listUrlRepository.deleteById(id);
    }
    public ListUrl getLienKetById(Long id) {
        return listUrlRepository.findById(id).orElse(null);
    }

    public void save(ListUrl lienKet) {
        listUrlRepository.save(lienKet);
    }

    public void themLienKet(String lienKetMoi) {
        ListUrl listUrl = new ListUrl();
        listUrl.khadung = 1;
        listUrl.linkRutGon = lienKetMoi;
        listUrl.time = String.valueOf(new Timestamp(System.currentTimeMillis()));
        listUrlRepository.save(listUrl);
    }

}
