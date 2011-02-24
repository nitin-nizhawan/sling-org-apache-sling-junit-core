/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.sling.junit;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class RequestParserTest {
    final String pathInfo;
    final String expectedTestSelector;
    final String expectedExtension;
    final String expectedMethodSelector;
    final String [] parseResult;
    
    public RequestParserTest(String pathInfo, String expectedTestSelector, String expectedExtension, String expectedMethodSelector) {
        this.pathInfo = pathInfo;
        this.expectedTestSelector = expectedTestSelector;
        this.expectedExtension = expectedExtension;
        this.expectedMethodSelector = expectedMethodSelector;
        this.parseResult = RequestParser.parsePathInfo(pathInfo);
        
        assertEquals(3, parseResult.length);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ", pathInfo=" + pathInfo;
    }
    
    @Test
    public void testSelector() {
        assertEquals(toString(), expectedTestSelector, parseResult[0]);
    }
    
    @Test
    public void testExtension() {
        assertEquals(toString(), expectedExtension, parseResult[1]);
    }
    
    @Test
    public void testMethodSelector() {
        assertEquals(toString(), expectedMethodSelector, parseResult[2]);
    }
    
    @Parameters
    public static Collection<Object[]> configs() {
        final String EMPTY= "";
        final Object[][] data = new Object[][] {
                { EMPTY, EMPTY, EMPTY, EMPTY },
                { "/", EMPTY, EMPTY, EMPTY },
                { "/.html", EMPTY, "html", EMPTY },
                { "/someTests.here.html", "someTests.here", "html", EMPTY },
                { "someTests.here.html", "someTests.here", "html", EMPTY },
                { "someTests.here.html.json", "someTests.here.html", "json", EMPTY },
        };
        
        return Arrays.asList(data);
     }
}