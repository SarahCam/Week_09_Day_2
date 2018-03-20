package controllers;

import db.DBHelper;
import models.Department;
import models.Engineer;
import models.Manager;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class EngineersController {


    public EngineersController() {

        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/engineers", (req, res) -> {
            Map<String, Object> model = new HashMap();
            List<Manager> engineers = DBHelper.getAll(Engineer.class);
            model.put("template", "templates/engineers/index.vtl");
            model.put("engineers", engineers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/engineers/new", (req, res) -> {
            Map<String, Object> model = new HashMap();
            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("template", "templates/engineers/create.vtl");
            model.put("departments", departments);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}

