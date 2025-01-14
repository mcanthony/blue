/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2006 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 */
package org.netbeans.modules.python.editor.lexer;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;

import org.netbeans.api.lexer.InputAttributes;
import org.netbeans.api.lexer.Language;
import org.netbeans.api.lexer.LanguagePath;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenId;
import org.netbeans.api.lexer.TokenUtilities;
import org.netbeans.spi.lexer.LanguageEmbedding;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;

/**
 *
 * @author Tor Norbye
 */
public enum PythonStringTokenId implements TokenId {
    STRING_TEXT("string"),
    STRING_ESCAPE("string-escape"),
    STRING_INVALID("string-escape-invalid"),
    URL("url"),
    EMBEDDED_PYTHON("string");
    private final String primaryCategory;

    PythonStringTokenId() {
        this(null);
    }

    PythonStringTokenId(String primaryCategory) {
        this.primaryCategory = primaryCategory;
    }

    public String primaryCategory() {
        return primaryCategory;
    }
    public static final Language<PythonStringTokenId> language =
            new LanguageHierarchy<PythonStringTokenId>() {
                @Override
                protected Collection<PythonStringTokenId> createTokenIds() {
                    return EnumSet.allOf(PythonStringTokenId.class);
                }

                @Override
                protected Map<String, Collection<PythonStringTokenId>> createTokenCategories() {
                    return null; // no extra categories
                }

                @Override
                protected Lexer<PythonStringTokenId> createLexer(
                        LexerRestartInfo<PythonStringTokenId> info) {
                    return new PythonStringLexer(info, true);
                }

                @Override
                protected LanguageEmbedding<?> embedding(
                        Token<PythonStringTokenId> token, LanguagePath languagePath,
                        InputAttributes inputAttributes) {
                    PythonStringTokenId id = token.id();

                    if (id == EMBEDDED_PYTHON && token.text() != null) {
                        return LanguageEmbedding.create(PythonTokenId.language(), 3, 0); // 3: Exlude ">>>" prefix
                    }

                    return null; // No embedding
                }

                @Override
                public String mimeType() {
                    return "text/x-python-string"; // NOI18N
                }
            }.language();

    public static Language<PythonStringTokenId> language() {
        return language;
    }
}
