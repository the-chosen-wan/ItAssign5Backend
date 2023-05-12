package com.net.assign5backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.net.assign5backend.entitity.BlogEntity;
import com.net.assign5backend.repository.BlogRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
@AllArgsConstructor
public class BlogService {
    List<BlogEntity> blogs;

    @Autowired
    private BlogRepository blogRepository;

    public BlogService() {
        blogs  = new ArrayList<BlogEntity>();
    }

    public void load(){
        blogs = blogRepository.findAll();
    }

    public void save(BlogEntity blog){
        blogRepository.save(blog);
        blogs.add(blog);
    }

    public void delete(int id){
        blogRepository.deleteById(id);
        blogs.removeIf(blog -> blog.getId() == id);
    }

    public BlogEntity getBlog(int id){
        return blogRepository.findById(id).get();
    }

    public void update(BlogEntity blog){
        blogRepository.save(blog);
        for(int i = 0; i < blogs.size(); i++){
            if(blogs.get(i).getId() == blog.getId()){
                blogs.set(i, blog);
                break;
            }
        }
    }

    private String deleteWhiteSpaceFromString(String str){
        return str.replaceAll("\\s", "");
    }

    private List<String> splitStringByComma(String str){
        List<String> result = new ArrayList<String>();
        String[] temp = str.split(",");
        for(String s : temp){
            result.add(s);
        }
        return result;
    }

    public List<BlogEntity> findByTags(String str){
        if(str == null || str.equals(""))
            return blogs;

        str = deleteWhiteSpaceFromString(str);
        List<BlogEntity> result = new ArrayList<BlogEntity>();

        List<String> tags = splitStringByComma(str);

        for(BlogEntity blog : blogs){
            for(String tag : tags){
                boolean found = false;
                List<String> blogTags = splitStringByComma(blog.getTags());
                for(String blogTag : blogTags){
                    if(tag.toLowerCase().equals(blogTag.toLowerCase())){
                        result.add(blog);
                        found = true;
                        break;
                    }
                }

                if(found)
                    break;
            }
        }

        return result;
    }
}
