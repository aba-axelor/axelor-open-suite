/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.production.rest.dto;

import com.axelor.apps.stock.db.StockMoveLine;
import com.axelor.apps.tool.api.ObjectFinder;
import com.axelor.apps.tool.api.RequestStructure;
import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ManufOrderProductPutRequest extends RequestStructure {

  @NotNull
  @Min(0)
  private Long stockMoveLineId;

  @NotNull
  @Min(0)
  private BigDecimal prodProductQty;

  public ManufOrderProductPutRequest() {};

  public long getStockMoveLineId() {
    return stockMoveLineId;
  }

  public void setStockMoveLineId(long stockMoveLineId) {
    this.stockMoveLineId = stockMoveLineId;
  }

  public BigDecimal getProdProductQty() {
    return prodProductQty;
  }

  public void setProdProductQty(BigDecimal prodProductQty) {
    this.prodProductQty = prodProductQty;
  }

  public StockMoveLine fetchStockMoveLine() {
    return ObjectFinder.find(StockMoveLine.class, stockMoveLineId, super.getVersion());
  }
}
