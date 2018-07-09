

    目标：自动生成@CLASSNAME@/add@CLASSNAME@.jsp、@CLASSNAME@/update@CLASSNAME@.jsp、@CLASSNAME@/list@CLASSNAME@.jsp
    
    目标实施步骤：
    
        1. 分析add_jsp_template结构
            1.1 from action 动态参数
            1.2 表单域 动态参数
        2. 创建add、list、updatep模板
        3. 分析传入参数
            2.1 outputDir
            2.2 filename
            2.3 mappings
        3. 编写build.xml
        4. 编写java类