module hello.modules {
    provides com.sims.modules.hello.HelloInterface with com.sims.modules.hello.HelloModules;
    exports com.sims.modules.hello;
}
