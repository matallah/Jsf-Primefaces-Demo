/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.forntend.controller;

import com.sbs.backend.main.entity.Dept;
import com.sbs.backend.main.entity.Emp;
import com.sbs.backend.main.service.IDeptService;
import com.sbs.forntend.converter.DepartmentConverter;
import com.sbs.forntend.scope.SpringScopeView;
import com.sbs.forntend.utils.JsfUtil;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;


@Named(value = "departmentBean")
@SpringScopeView
public class DepartmentController implements Serializable {

    Dept oldDept;
    Dept currentDept;

    boolean addRecord = true;
    @Autowired
    private IDeptService deptService;
    private List<Dept> departments;
    private boolean editingMode;

    /**
     * Creates a new instance of EmployeeBean
     */
    public DepartmentController() {
        oldDept = new Dept();
        currentDept = new Dept();
    }

    @PostConstruct
    public void init() {
        departments = deptService.findAllDepts();
    }

    public void edit(RowEditEvent event) {
        addRecord = false;

        currentDept = (Dept) event.getObject();

        try {
            //Check To Specifiy Which Action To Take insert or update.
            if (currentDept.getDeptno() == null) {
                //Persist
                deptService.insert(currentDept);
            } else {
                //Update
                deptService.update(currentDept);
            }
            JsfUtil.messageInfo("Saved Successfully");
        } catch (Exception e) {
            JsfUtil.messageInfo("Something goes wrong. Try Again.");
        }

    }

    public void onRowEditInit(Dept dept) {
        if (dept != null) {
            oldDept = dept;
        } else {
            oldDept = null;
        }
    }

    public void onRowEditCancel(Dept dept) {
        if (dept.getDeptno() != null) {
        } else {
            departments.remove(dept);
        }
        addRecord = true;
    }

    public void addRecord() {
        departments.add(currentDept);
        currentDept = new Dept();
        addRecord = false;
    }

    public void delete(Dept dept) {
        try {
            deptService.delete(dept);
            departments.remove(dept);
            JsfUtil.messageInfo("Deleted Successfully.");
        } catch (Exception e) {
            JsfUtil.messageInfo("Something goes wrong. Try Again.");
        }
    }

    public List<Dept> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Dept> departments) {
        this.departments = departments;
    }

    public boolean isEditingMode() {
        return editingMode;
    }

    public void setEditingMode(boolean editingMode) {
        this.editingMode = editingMode;
    }

    public Dept getOldDept() {
        return oldDept;
    }

    public void setOldDept(Dept oldDept) {
        this.oldDept = oldDept;
    }

    public Dept getCurrentDept() {
        return currentDept;
    }

    public void setCurrentDept(Dept currentDept) {
        this.currentDept = currentDept;
    }

    public boolean isAddRecord() {
        return addRecord;
    }

    public void setAddRecord(boolean addRecord) {
        this.addRecord = addRecord;
    }
}
