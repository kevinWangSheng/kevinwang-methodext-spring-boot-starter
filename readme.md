## 拦截方法中间件
使用示例
```java
@GetMapping("/hello")
    @DoMethodext(method = "getHello",returnJson = "{\"code\":\"1111\",\"desc\":\"调用方法超过最大次数，限流返回！！\"}")
    public Result hello(){
        Result result = new Result();
        result.setCode("123123");
        result.setDesc("wangwu");
        return result;
    }

    public boolean getHello(){
        isTrue = !isTrue;
        return isTrue;
    }
```
这个method是你要拦截前执行的方法，是调用执行当前类去执行的，所以这个方法你要定义在当前方法的同一个类中

returnJson表示拦截以后，不符合执行方法默认的返回值

这里使用了aop+反射+注解的方式获取插入的方法，主要是用于一些白名单黑名单方法拦截比较多

所以这个插入的方法必须返回boolean类型的