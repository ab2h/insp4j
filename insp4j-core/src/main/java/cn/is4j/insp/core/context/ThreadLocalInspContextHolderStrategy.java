/*
 * Copyright 2020 zengzhihong All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.is4j.insp.core.context;

import org.springframework.util.Assert;

/**
 * @author zengzhihong
 */
public class ThreadLocalInspContextHolderStrategy implements InspContextHolderStrategy {

    private static final ThreadLocal<InspContext> contextHolder = new ThreadLocal<>();


    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public InspContext getContext() {
        InspContext ctx = contextHolder.get();
        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    @Override
    public void setContext(InspContext context) {
        Assert.notNull(context, "Only non-null InspContext instances are permitted");
        contextHolder.set(context);
    }

    @Override
    public InspContext createEmptyContext() {
        return new InspContextImpl();
    }
}