package controllers;
import db.DBHelper;
import db.Seeds;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EmployeesController {

    public static void main(String[] args) {

        staticFileLocation("public");

        ManagersController managersController = new ManagersController();   // Will create a new instance of Manager Controller
        Seeds.seedData();

        get("/employees", (req, res) -> {
            Map<String, Object> model = new HashMap();
            List<Employee> employees = DBHelper.getAll(Employee.class);
            model.put("template", "templates/employees/index.vtl");
            model.put("employees", employees);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}