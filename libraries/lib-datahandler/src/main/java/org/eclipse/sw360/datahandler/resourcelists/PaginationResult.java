/*
 * Copyright Bosch Software Innovations GmbH, 2018.
 * Part of the SW360 Portal Project.
 *
 * SPDX-License-Identifier: EPL-1.0
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.sw360.datahandler.resourcelists;

import java.util.Comparator;
import java.util.List;

public class PaginationResult<T> {

    private final List<T> resources;
    private final int totalCount;
    private final PaginationOptions<T> paginationOptions;
    private final Boolean pagingActive;

    public PaginationResult(List<T> resources) {
        this.resources = resources;
        totalCount = resources.size();
        paginationOptions = new PaginationOptions<>(1, totalCount, Comparator.comparing(x -> true));
        pagingActive = false;
    }

    PaginationResult(List<T> resources, int totalCount, PaginationOptions<T> paginationOptions) {
        this.resources = resources;
        this.totalCount = totalCount;
        this.paginationOptions = paginationOptions;
        pagingActive = true;
    }

    public List<T> getResources() {
        return resources;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public PaginationOptions<T> getPaginationOptions() {
        return paginationOptions;
    }

    public int getTotalPageCount() {
        int numberOfFullPages = getTotalCount() / paginationOptions.getPageSize();
        boolean hasAdditionalNotFullPage = getTotalCount() % paginationOptions.getPageSize() != 0;
        return numberOfFullPages + (hasAdditionalNotFullPage ? 1 : 0);
    }

    public boolean isPagingActive() {
        return pagingActive;
    }
}
