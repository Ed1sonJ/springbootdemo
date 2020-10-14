package com.dxj.springbootdemo.controller;

import com.dxj.springbootdemo.service.HelloService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        String hello = helloService.hello();
        return hello;
    }


    /**
     * XXX?list=123,345,567
     *
     * @param list
     * @return
     */
    @GetMapping("/downloadFile")
    public HttpServletResponse downloadFile(@RequestParam(value = "list") List<String> list, HttpServletResponse response) {

        response.setContentType("application/vnd..ms-excel");
        response.setHeader("content-Disposition","attachment;filename=abc.xlsx");

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        try {
            hssfWorkbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}

//https://blog.csdn.net/baidu_28068985/article/details/83035727

//    @ApiOperation(value = "导出地址数据")
//    @PostMapping(value="/exportAddresses")
//    public ApiResponse<Object> exportAddresses(HttpServletResponse response){
//        ApiResponse<Object> resp = new ApiResponse<Object>();
//
//        String[] titles = new String[] {"id","name","pid","code","description"};
//        List<Map<String,Object>> objList = new ArrayList<>();
//
//        List<AddressVo> list = addressService.exportAddresses();
//        for(AddressVo item : list){
//            Map<String,Object> tempMap = new HashMap<>();
//            tempMap.put("id", item.getId());
//            tempMap.put("name", item.getName());
//            tempMap.put("pid", item.getPid());
//            tempMap.put("code", item.getCode());
//            tempMap.put("description", item.getDescription());
//            objList.add(tempMap);
//        }
//        try {
//            FileUtil.exportExcel(response,"地址树",titles,objList);
//            resp.ok("导出成功！");
//        }catch (Exception e){
//            e.printStackTrace();
//            resp.error("导出失败！");
//        }
//        return resp;


//    public static void exportExcel(HttpServletResponse response,String fileName,String[] titles,List<Map<String,Object>> result){
//        HSSFWorkbook wb;
//        OutputStream output = null;
//        String tempName = fileName;
//        try {
//            Date date = new Date();
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            fileName +="_"+df.format(date)+".xls";
//
//            wb= new HSSFWorkbook();
//            HSSFSheet sh = wb.createSheet();
//
//            // 设置列宽
//            for(int i = 0; i < titles.length-1; i++){
//                sh.setColumnWidth( i, 256*15+184);
//            }
//
//            // 第一行表头标题，CellRangeAddress 参数：行 ,行, 列,列
//            HSSFRow row = sh.createRow(0);
//            HSSFCell cell = row.createCell(0);
//            cell.setCellValue(new HSSFRichTextString(tempName));
//            cell.setCellStyle(fontStyle(wb));
//            sh.addMergedRegion(new CellRangeAddress(0, 0, 0,titles.length-1));
//
//            // 第二行
//            HSSFRow row3 = sh.createRow(1);
//
//            // 第二行的列
//            for(int i=0; i < titles.length; i++){
//                cell = row3.createCell(i);
//                cell.setCellValue(new HSSFRichTextString(titles[i]));
//                cell.setCellStyle(fontStyle(wb));
//            }
//
//            //填充数据的内容  i表示行，z表示数据库某表的数据大小，这里使用它作为遍历条件
//            int i = 2, z = 0;
//            while (z < result.size()) {
//                row = sh.createRow(i);
//                Map<String,Object> map = result.get(z);
//                for(int j=0;j < titles.length;j++) {
//                    cell = row.createCell(j);
//                    if(map.get(titles[j]) !=null) {
//                        cell.setCellValue(map.get(titles[j]).toString());
//                    }else {
//                        cell.setCellValue("");
//                    }
//                }
//                i++;
//                z++;
//            }
//
//            output = response.getOutputStream();
//            response.reset();
//            response.setHeader("Content-disposition", "attachment; filename="+fileName);
//            response.setContentType("application/msexcel");
//            wb.write(output);
//            output.flush();
//            output.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }