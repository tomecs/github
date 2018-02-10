package githubsourcegenerator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import cn.org.rapid_framework.generator.GeneratorProperties;

public class CodeGeneratorMain {
    public static void main(String[] args) {
        GeneratorFacade g = new GeneratorFacade();
        try{
            // ---------------------start 需修改部分 start ----------------------------
            //指定需要生成相关信息的model
            Class clazz = Demo.class;
            //指定输出目录
            String outRootDir = "D:\\123";
            //指定基础包名
            GeneratorProperties.setProperty("basepackage","com.vortex");
            //指定模块名称
            GeneratorProperties.setProperty("application","jnhj");
            // ----------------------end 需修改部分 end ----------------------------

            g.getGenerator().setTemplateRootDir("classpath:template");
            ///g.deleteOutRootDir();
            GeneratorProperties.setProperty("outRoot",outRootDir);
            g.getGenerator().setOutRootDir(outRootDir);
            String clazzSimpleName = clazz.getSimpleName();
            GeneratorProperties.setProperty("className",clazzSimpleName);
            g.generateByClass(clazz);
            //打开文件夹
            Runtime.getRuntime().exec("cmd.exe /c start "+ GeneratorProperties.getRequiredProperty("outRoot"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
