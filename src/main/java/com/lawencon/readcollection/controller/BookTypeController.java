package com.lawencon.readcollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.dto.booktype.BookTypeInsertReqDto;
import com.lawencon.readcollection.dto.booktype.BookTypeUpdateReqDto;
import com.lawencon.readcollection.model.BookType;
import com.lawencon.readcollection.service.BookTypeService;

@RestController
@RequestMapping("book-types")
public class BookTypeController {
    
    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping
    public ResponseEntity<BaseResListDto<BookType>> getAll(@RequestParam(value="search",required = false,defaultValue = "-") Object search){
        BaseResListDto<BookType> baseResListDto = null;

        if(search.equals("-")){
            baseResListDto = bookTypeService.getAll();
        }else{
            baseResListDto = bookTypeService.getAll(search);
        }

        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    @GetMapping("{id}/id")
    public ResponseEntity<BaseResSingleDto<BookType>> getById(@PathVariable("id") String id){
        BaseResSingleDto<BookType> baseResSingleDto = bookTypeService.getById(id);

        return new ResponseEntity<>(baseResSingleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInsertResDto> save(@RequestBody BookTypeInsertReqDto bookTypeInsertReqDto){
        BaseInsertResDto baseInsertResDto = bookTypeService.save(bookTypeInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = bookTypeService.update(bookTypeUpdateReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }
}
