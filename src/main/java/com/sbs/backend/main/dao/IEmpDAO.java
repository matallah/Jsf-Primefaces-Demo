/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.backend.main.dao;

import com.sbs.backend.main.dao.base.IBaseDAO;
import com.sbs.backend.main.entity.Emp;

import java.util.List;


public interface IEmpDAO extends IBaseDAO<Emp> {

    public Emp find(int id);

    public List<Emp> findAll();

}
