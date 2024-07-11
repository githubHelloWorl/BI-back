package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Excel 相关工具类
 */
@Slf4j
public class ExeclUtils {

    /**
     * 转 csv
     * @param multipartFile
     * @return
     * @throws FileNotFoundException
     */
    public static String execlToCsv(MultipartFile multipartFile) {
//        File file = ResourceUtils.getFile("classpath:test.execl.xlsx");
        List<Map<Integer,String>> list = null;
        try{
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
//            System.out.println(list);
        }catch(IOException e){
            log.error("execl 表格处理错误", e);
        }

        // 进行判断
        if(CollUtil.isEmpty(list)){
            return "";
        }

        // 转换为 csv
        StringBuilder stringBuilder = new StringBuilder();
        // 读取表头
        LinkedHashMap<Integer, String> headerMap = (LinkedHashMap<Integer, String>) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//        System.out.println(StringUtils.join(headerList, ","));
        stringBuilder.append(StringUtils.join(headerList, ",")).append("\n");
        // 读取数据
        for(int i = 1; i < list.size(); ++i ){
            LinkedHashMap<Integer, String> dataMap = (LinkedHashMap<Integer, String>) list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
//            System.out.println(StringUtils.join(dataMap.values(), ","));
            stringBuilder.append(StringUtils.join(dataList, ",")).append("\n");
        }
//        System.out.println(list);
        return stringBuilder.toString();
    }
}
