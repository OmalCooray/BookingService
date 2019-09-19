package controllers

import javax.inject.Inject
import models.requests.BookingHandler
import play.api.mvc.{AbstractController, ControllerComponents}

import scala.concurrent.{ExecutionContext}

class BookingController  @Inject()(cc: ControllerComponents)(implicit exec: ExecutionContext)
  extends AbstractController(cc) {

  val handler = new BookingHandler()

    def createBooking() = Action.async(parse.json){
      implicit request => {
        val res = handler.createBooking(request.body)
        res.map {
          case bookingResponse => Ok(bookingResponse)
          case _ => BadRequest("Bad Request")
        }
      }
    }
}
