#lang racket

(require "utils.scm")

;;Create : () --> BinaryNum
(define (create)
  (list 0))

;;Add-bit: BinaryNum X Bit --> BinaryNum
(define (add-bit BinaryNum bit)
  (append BinaryNum (list bit)))

;;Rotate-left: BinaryNum --> BinaryNum
(define (rotate-left BinaryNum)
  (append (cdr BinaryNum) (list (car BinaryNum))))

  
;;Rotate-right: BinaryNum --> BinaryNum
(define (rotate-right BinaryNum)
  (append (list (car (reverse BinaryNum))) (reverse (cdr (reverse BinaryNum)))))

;;Change-bit: BinaryNum X index X Bit --> BinaryNum
(define (change-bit BinaryNum index bit)
  (append (reverse (list-tail(reverse BinaryNum) (+ 1 index))) (list bit) (list-tail BinaryNum (- (length BinaryNum) index ))))

;To-base-10: BinaryNum --> int
(define (to-base-10 BinaryNum)
  (cond
    ((empty? BinaryNum) 0)
    ((eqv? (car BinaryNum) 0 ) (to-base-10 (cdr BinaryNum)))
    (else (+ (expt 2 (- (length BinaryNum) 1)) (to-base-10 (cdr BinaryNum))))))
      

;;reverse helper procedure
;  (define (reverse lst)
;    (if (null? lst) '()
;        (append (reverse (cdr lst)) (list (car lst)))))

;-------------------------------------------------------------------------
;Unit tests

(equal?? (create ) '(0))
(report-unit-tests-completed 'create)
(equal?? (add-bit '(0 1 0) 1) '(0 1 0 1))
(report-unit-tests-completed 'Add-bit)
(equal?? (rotate-right '(0 1 1 1 0 1)) '(1 0 1 1 1 0))
(report-unit-tests-completed 'rotate-right)
(equal?? (rotate-left '(0 1 1 1 0 1)) '(1 1 1 0 1 0))
(report-unit-tests-completed 'rotate-left)
(equal?? (change-bit  '(0 1 1 1 0 1) 2 0) '(0 1 1 0 0 1))
(report-unit-tests-completed 'change-bit)
(equal?? (to-base-10 '(1 1 0 1)) 13)
(report-unit-tests-completed 'to-base-10)
