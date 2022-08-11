/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbs.forntend.controller;

import com.sbs.backend.main.entity.Dept;
import com.sbs.backend.main.entity.Emp;
import com.sbs.backend.main.service.IDeptService;
import com.sbs.backend.main.service.IEmpService;
import com.sbs.forntend.converter.DepartmentConverter;
import com.sbs.forntend.scope.SpringScopeView;
import com.sbs.forntend.utils.JsfUtil;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named(value = "employeeBean")
@SpringScopeView
public class EmployeeController implements Serializable {

    Emp oldEmployee;
    Emp currentEmployee;
    boolean addRecord = true;
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    private List<Emp> employees;
    private List<Dept> departments;
    private List<String> genders;
    private boolean editingMode;
    private DepartmentConverter departmentConverter;

    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeController() {
        oldEmployee = new Emp();
        currentEmployee = new Emp();
        genders = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        employees = empService.findAllEmployees();
        departments = deptService.findAllDepts();
        departmentConverter = new DepartmentConverter(departments);
        genders.add("Male");
        genders.add("female");
    }

    public void edit(RowEditEvent event) {
        addRecord = false;

        currentEmployee = (Emp) event.getObject();

        try {
            //Check To Specifiy Which Action To Take insert or update.
            if (currentEmployee.getEmpno() == null) {
                //Persist
                empService.insert(currentEmployee);
            } else {
                //Update
                empService.update(currentEmployee);
            }
            JsfUtil.messageInfo("Saved Successfully");
        } catch (Exception e) {
            JsfUtil.messageInfo("Something goes wrong. Try Again.");
        }

    }

    public void onRowEditInit(Emp emp) {
        if (emp != null) {
            oldEmployee = emp;
        } else {
            oldEmployee = null;
        }
    }

    public void onRowEditCancel(Emp emp) {
        if (emp.getEmpno() != null) {
        } else {
            employees.remove(emp);
        }
        addRecord = true;
    }

    public void addRecord() {
        employees.add(currentEmployee);
        currentEmployee = new Emp();
        addRecord = false;
    }

    public void delete(Emp emp) {
        try {
            empService.delete(emp);
            employees.remove(emp);
            JsfUtil.messageInfo("Deleted Successfully.");
        } catch (Exception e) {
            JsfUtil.messageInfo("Something goes wrong. Try Again.");
        }
    }

    public enum GenderEnum {
        MALE, FEMALE
    }

    public List<Emp> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Emp> employees) {
        this.employees = employees;
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

    public DepartmentConverter getDepartmentConverter() {
        return departmentConverter;
    }

    public void setDepartmentConverter(DepartmentConverter departmentConverter) {
        this.departmentConverter = departmentConverter;
    }

    public List<String> getGenders() {
        return genders;
    }

    public void setGenders(List<String> genders) {
        this.genders = genders;
    }

}
