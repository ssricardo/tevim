package org.rss.tools.tevim.global

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.*
import org.slf4j.LoggerFactory
import java.util.*

/**
 * Logging Aspect.
 * @author ricardo saturnino
 */
@Aspect
class ApplicationLogger {

    @Pointcut("execution(* org.rss.tools.tevim.parsing..*(..))")
    fun allMethods() {
    }

    @Around("allMethods()")
    @Throws(Throwable::class)
    fun aroundAll(pjp: ProceedingJoinPoint): Any {
        LOG.debug("Start {}.{}", pjp.signature.declaringType, pjp.signature.name)
        if (LOG.isTraceEnabled) {
            LOG.trace(Arrays.toString(pjp.args))
        }

        try {
            return pjp.proceed()
        } finally {
            LOG.trace("Method {} complete", pjp.signature.name)
        }
    }

    // ---------------------- Main Logger ------------------------

    @Before("execution(* org.rss.tools.tevim.Main.main(..))")
    fun startMain() {
        LOG.info("Starting Tevim process......................")
    }

    @After("execution(* org.rss.tools.tevim.Main.main(..))")
    fun allDone() {
        LOG.info("Execution ended ......................")
    }

    @Before("call(* org.rss.tools.tevim.Main.processParse(..))")
    fun startParse() {
        LOG.info("* Executing PARSE phase......................")
    }

    @Before("call(* org.rss.tools.tevim.Main.processRender(..))")
    fun startRender() {
        LOG.info("* Executing RENDER phase......................")
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(ApplicationLogger::class.java)
    }
}
