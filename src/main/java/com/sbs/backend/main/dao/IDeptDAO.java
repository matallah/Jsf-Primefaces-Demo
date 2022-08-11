/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.backend.main.dao;

import com.sbs.backend.main.dao.base.IBaseDAO;
import com.sbs.backend.main.entity.Dept;

import java.util.List;


public interface IDeptDAO extends IBaseDAO<Dept> {

    public Dept find(int id);

    public List<Dept> findAll();

}
