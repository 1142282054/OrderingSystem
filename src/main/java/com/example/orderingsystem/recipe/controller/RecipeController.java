package com.example.orderingsystem.recipe.controller;

import com.example.orderingsystem.comment.vo.Result;
import com.example.orderingsystem.recipe.po.FoodClass;
import com.example.orderingsystem.recipe.po.Recipe;
import com.example.orderingsystem.recipe.service.RecipeService;
import com.example.orderingsystem.recipe.vo.RecipePage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author MinZhi
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Resource
    RecipeService recipeService;

    /**
     * 获取菜谱展示列表
     * 1.获取所有菜谱,存入session用于数据回显
     * 2.跳转页面
     * @param session
     * @return
     */
    @RequestMapping("/page")
    public String recipe(HttpSession session){
        List<Recipe> recipeList = recipeService.getAllRecipe();
//        log.info(recipeList.toString());
        session.setAttribute("recipeList",recipeList);
        return "manager/recipe";
    }

    /**
     * 获取菜谱信息
     * @param page 分页条件,限制条件(菜名name)
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRecipe")
    public Result getRecipe(RecipePage page){
        List<Recipe> recipeList = recipeService.getRecipeList(page);
        if (recipeList != null){
            return Result.successful(recipeList,(long)recipeService.getAllRecipe().size());
        }
        return Result.fail();
    }

    @RequestMapping("/addRecipePage")
    public String addRecipePage(){
        return "manager/recipe/add";
    }


//Resful风格
    /**
     * 更新菜谱页面
     * 1.获取菜信息并存入session用于数据回显
     * 2.跳转页面
     * @param cid 菜id
     * @param session
     * @return
     */
    @GetMapping("/{cid}")
    public String updateRecipePage(@PathVariable("cid") Integer cid, HttpSession session){
        Recipe recipe = recipeService.getRecipeByCid(cid);
        if (recipe != null){
            session.setAttribute("recipe",recipe);
        }
        return "manager/recipe/edit";
    }

    /**
     * 添加菜谱
     * @param recipe 菜信息
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/addRecipe")
    public Result addRecipe(Recipe recipe) throws IOException {
//        if (file.isEmpty()) {
//            return Result.fail();
//        }
//        InputStream is = file.getInputStream();
//        byte[] pic = new byte[(int)file.getSize()];
//        is.read(pic);
//        recipe.setPhoto(pic);

        boolean add = recipeService.addRecipe(recipe);

        if (add == false){
            return Result.fail();
        }
        return Result.successful();
    }

    /**
     * 更新菜的图片
     * @param file 图片信息
     * @param name 菜名
     * @return
     * @throws IOException
     */
    @ResponseBody
    @PostMapping("/addRecipePic")
    public Result addRecipePic(@RequestParam("file")MultipartFile file,String name) throws IOException {
        if (file.isEmpty()) {
            return Result.fail();
        }
        InputStream is = file.getInputStream();
        byte[] pic = new byte[(int)file.getSize()];
        is.read(pic);


        boolean add = recipeService.addRecipePic(pic,name);

        if (add == false){
            return Result.fail();
        }
        return Result.successful();
    }

    /**
     * 获取菜图片
     * @param response
     * @param cid 菜id
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/getRecipePic")
    public void getRecipePic(HttpServletResponse response,Integer cid) throws IOException {
//        byte[] data = recipeService.getRecipePic(cid);
        Recipe recipe = recipeService.getRecipeById(cid);
        byte[] data = recipe.getPhoto();
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("UTF-8");
        OutputStream outputSream = response.getOutputStream();
        InputStream in = new ByteArrayInputStream(data);
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = in.read(buf, 0, 1024)) != -1) {
            outputSream.write(buf, 0, len);
        }
        outputSream.close();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @DeleteMapping("/{ids}")
    public Result deleteRecipe(@PathVariable("ids") String ids){
        boolean delete = recipeService.deleteRecipes(ids);
        if (delete == false){
            return Result.fail();
        }
        return Result.successful();
    }

    /**
     * 更新菜谱
     * @param recipe
     * @return
     */
    @ResponseBody
    @PutMapping("")
    public Result updateRecipe(Recipe recipe){
        boolean update = recipeService.updateRecipe(recipe);
        if (update == false){
            return Result.fail();
        }
        return Result.successful();
    }

//    @ResponseBody
//    @RequestMapping("/upPic")
//    public Result testUp(@RequestParam("file")MultipartFile photo,HttpSession session) throws IOException {
//        //获取上传的文件的文件名
//        String fileName = photo.getOriginalFilename();
//        //获取上传的文件的后缀名
//        String hzName = fileName.substring(fileName.lastIndexOf("."));
//        //获取uuid
//        String uuid = UUID.randomUUID().toString();
//        //拼接一个新的文件名
//        fileName = uuid + hzName;
//        //获取ServletContext对象
//        ServletContext servletContext = session.getServletContext();
//        //获取当前工程下photo目录的真实路径
//        String photoPath = servletContext.getRealPath("pic");
//        //创建photoPath所对应的File对象
//        File file = new File(photoPath);
//        //判断file所对应目录是否存在
//        if(!file.exists()){
//            file.mkdir();
//        }
//        String finalPath = photoPath + File.separator + fileName;
//        //上传文件
//        photo.transferTo(new File(finalPath));
//        return Result.successful("fileName",null);
//    }

}
